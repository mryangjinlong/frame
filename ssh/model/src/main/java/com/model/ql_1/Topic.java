package com.model.ql_1;

import org.hibernate.annotations.BatchSize;

import java.util.Date;

import javax.persistence.*;

//代表一个主题
@Entity
//将查询语句写在model里面然后起一直名字   然后使用 Query q = session.getNamedQuery("topic.selectCertainTopic");
@NamedQueries(
		{
				@NamedQuery(name="topic.selectCertainTopic", query="from Topic t where t.id = :id")
		}
)
		/*
@NamedNativeQueries(
		{
			@NamedNativeQuery(name="topic.select2_5Topic", query="select * from topic limit 2, 5")
		}
		)
		*/
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@ManyToOne(fetch=FetchType.EAGER)
	private Category category;
	//private Category category2;
	
	private Date createDate;

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
