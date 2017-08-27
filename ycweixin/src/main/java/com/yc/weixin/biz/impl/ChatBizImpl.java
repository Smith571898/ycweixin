package com.yc.weixin.biz.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yc.weixin.bean.Chat_log;
import com.yc.weixin.bean.Joke;
import com.yc.weixin.bean.Knowledge;
import com.yc.weixin.bean.Knowledge_sub;
import com.yc.weixin.biz.ChatBiz;
import com.yc.weixin.dao.BaseDao;

@Service
public class ChatBizImpl implements ChatBiz {
	
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Override
	public String getIndexDir() {
		// 得到.class文件所在路径（WEB-INF/classes/）
		String classpath = ChatBizImpl.class.getResource("/").getPath();
		// 将classpath中的%20替换为空格
		classpath = classpath.replaceAll("%20", " ");
		// 索引存储位置：WEB-INF/classes/index/
		return classpath + "index/";
	}

	@Override
	public void createIndex() {
		// 取得问答知识库中的所有记录
		List<Knowledge> knowledgeList = baseDao.findAll(Knowledge.class, "FindAllKnowledge");
		Directory directory = null;
		IndexWriter indexWriter = null;
		try {
			directory = FSDirectory.open(new File(getIndexDir()));
			IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_46, new IKAnalyzer(true));
			indexWriter = new IndexWriter(directory, iwConfig);
			Document doc = null;
			// 遍历问答知识库创建索引
			for (Knowledge knowledge : knowledgeList) {
				doc = new Document();
				// 对question进行分词存储
				doc.add(new TextField("question", knowledge.getQuestion(), Store.YES));
				// 对id、answer和category不分词存储
				doc.add(new IntField("id", knowledge.getId(), Store.YES));
				doc.add(new StringField("answer", knowledge.getAnswer(), Store.YES));
				doc.add(new IntField("category", knowledge.getCategory(), Store.YES));
				indexWriter.addDocument(doc);
			}
			indexWriter.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从索引文件中根据问题检索答案
	 * 
	 * @param content
	 * @return Knowledge
	 */
	@SuppressWarnings("deprecation")
	private Knowledge searchIndex(String content) {
		Knowledge knowledge = null;
		try {
			Directory directory = FSDirectory.open(new File(getIndexDir()));
			IndexReader reader = IndexReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);
			// 使用查询解析器创建Query
			QueryParser questParser = new QueryParser(Version.LUCENE_46, "question", new IKAnalyzer(true));
			Query query = questParser.parse(QueryParser.escape(content));
			// 检索得分最高的文档
			TopDocs topDocs = searcher.search(query, 1);
			if (topDocs.totalHits > 0) {
				knowledge = new Knowledge();
				ScoreDoc[] scoreDoc = topDocs.scoreDocs;
				for (ScoreDoc sd : scoreDoc) {
					Document doc = searcher.doc(sd.doc);
					knowledge.setId(doc.getField("id").numericValue().intValue());
					knowledge.setQuestion(doc.get("question"));
					knowledge.setAnswer(doc.get("answer"));
					knowledge.setCategory(doc.getField("category").numericValue().intValue());
				}
			}
			reader.close();
			directory.close();
		} catch (Exception e) {
			knowledge = null;
			e.printStackTrace();
		}
		return knowledge;
	}

	@Override
	public String chat(String openId, String createTime, String question) {
		Chat_log chat_log = new Chat_log();
		chat_log.setOpen_id(openId);
		chat_log.setCreate_time(createTime);
		String answer = null;
		int chatCategory = 0;
		Knowledge knowledge = searchIndex(question);
		// 找到匹配项
		if (null != knowledge) {
			// 笑话
			if (2 == knowledge.getCategory()) {
				Joke joke = (Joke) baseDao.findOne(Joke.class, "findOneJoke");
				answer = joke.getJoke_content();
				chatCategory = 2;
			}
			// 上下文
			else if (3 == knowledge.getCategory()) {
				// 判断上一次的聊天类别
				chat_log = (Chat_log) baseDao.findOne(chat_log, "Findchat_categoryByOpenid");
				int category = chat_log.getChat_category();
				// 如果是笑话，本次继续回复笑话给用户
				if (2 == category) {
					Joke joke = (Joke) baseDao.findOne(Joke.class, "findOneJoke");
					answer = joke.getJoke_content();
					chatCategory = 2;
				} else {
					answer = knowledge.getAnswer();
					chatCategory = knowledge.getCategory();
				}
			}
			// 普通对话
			else {
				answer = knowledge.getAnswer();
				// 如果答案为空，根据知识id从问答知识分表中随机获取一条
				if ("".equals(answer)){
					Knowledge_sub k_sub = new Knowledge_sub();
					k_sub.setPid(knowledge.getId());
					k_sub = (Knowledge_sub) baseDao.findOne(k_sub, "findAnswerBypid");
					answer = k_sub.getAnswer();
				}
				chatCategory = 1;
			}
		}
		// 未找到匹配项
		else {
			answer = getDefaultAnswer();
			chatCategory = 0;
		}
		// 保存聊天记录
		chat_log.setOpen_id(openId);
		chat_log.setCreate_time(createTime);
		chat_log.setReq_msg(question);
		chat_log.setResp_msg(answer);
		chat_log.setChat_category(chatCategory);
		baseDao.save(chat_log, "addChat_log");
		return answer;
	}

	/**
	 * 随机获取一个默认的答案
	 * 
	 * @return
	 */
	private String getDefaultAnswer() {
		String[] answer = { "要不我们聊点别的？", "恩？你到底在说什么呢？", "没有听懂你说的，能否换个说法？", "虽然不明白你的意思，但我却能用心去感受",
				"听的我一头雾水，阁下的知识真是渊博呀，膜拜~", "真心听不懂你在说什么，要不你换种表达方式如何？", "哎，我小学语文是体育老师教的，理解起来有点困难哦",
				"是世界变化太快，还是我不够有才？为何你说话我不明白？" };
		return answer[getRandomNumber(answer.length)];
	}

	/**
	 * 随机生成 0~length-1 之间的某个值
	 * 
	 * @return int
	 */
	private int getRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}
	
	/**
	 * 从数据库里面读取指定日期的聊天记录 或者所有的聊天记录
	 */
	@Override
	public List<Chat_log> findAllChat_Log(Map map) {
		List<Chat_log> list =this.baseDao.findAll(Chat_log.class, "findAllChatLog", map);
		return list;
	}

	@Override
	public Integer getChatLogCount() {
		Integer total=this.baseDao.getFunc(Chat_log.class, "getAllChatLogCount");
		return total;
	}

	@Override
	public List<Chat_log> findChatLogByDate(Map map) {
		List<Chat_log> list =this.baseDao.findAll(Chat_log.class, "findAllChatLog", map);
		return list;
	}

	@Override
	public Integer getChatLogCountByDate(Map map) {
		Integer total=this.baseDao.getFunc(Chat_log.class, "getAllChatLogCountByDate", map);
		return total;
	}
}
