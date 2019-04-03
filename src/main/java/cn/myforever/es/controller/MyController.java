package cn.myforever.es.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.myforever.es.dao.TopicDao;
import cn.myforever.es.entity.Topic;
/**
 * ES控制类
 * @author suibibk.com
 */
@RestController
public class MyController {
	@Autowired
	private TopicDao topicDao;
	/**
	 * 保存博文
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		Long id = System.currentTimeMillis();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Topic topic =new Topic();
		topic.setId(id);
		topic.setTitle(title);
		topic.setContent(content);
		topicDao.save(topic);
		System.out.println("topicid:"+id);
		return id+"";
	}
	/**
	 * 根据ID查找
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectById")
	public Topic selectById(HttpServletRequest request) {
		String id = request.getParameter("id");
		//下面这里返回结果
		Optional<Topic> t = topicDao.findById(Long.parseLong(id));
		return t.get();
	}
	/**
	 * 根据用户的输入查找，虽然传的是content，这里只是名称定义而已，会搜索title和content都含有的记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectBycontent")
	public List<Topic> selectBycontent(HttpServletRequest request) {
		String content = request.getParameter("content");
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(content);
		Iterable<Topic> searchResult = topicDao.search(builder);
		Iterator<Topic> topics = searchResult.iterator();
		List<Topic> list = new ArrayList<Topic>();
		while(topics.hasNext()) {
			list.add(topics.next());
		}
		return list;
	}
	/**
	 * 分页搜索，pageNum是从0开始的
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectBycontentPage")
	public List<Topic> selectBycontentPage(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		int pageSize=2;
		String content = request.getParameter("content");
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(content);
		Pageable pageable = PageRequest.of(Integer.parseInt(pageNum), pageSize);
		// 分页参数
		Iterable<Topic> searchResult = topicDao.search(builder,pageable);
		Iterator<Topic> topics = searchResult.iterator();
		List<Topic> list = new ArrayList<Topic>();
		while(topics.hasNext()) {
			list.add(topics.next());
		}
		System.out.println("数据大小："+list.size());
		return list;
	}
}
