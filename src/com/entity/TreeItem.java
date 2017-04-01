package com.entity;

import java.util.ArrayList;
import java.util.List;

public class TreeItem {

	private String id;

	private String text;

	private boolean leaf;

	private boolean expanded;

	private String iconCls;

	private List<TreeItem> children;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<TreeItem> getChildren() {
		if (children == null)
			children = new ArrayList<TreeItem>();
		return children;
	}

}
