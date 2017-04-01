package com.inms.action.tools;
 
/**
 * @author 高远</n> 邮箱：wgyscsf@163.com</n> 博客 http://blog.csdn.net/wgyscsf</n>
 *     编写时期 2016-4-4 下午2:09:27
 */
public class TextMsg {
  private String ToUserName;
  private String FromUserName;
  private long CreateTime;
  private String MsgType;
 
  @Override
  public String toString() {
    return "TextMsg [ToUserName=" + ToUserName + ", FromUserName="
        + FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
        + MsgType + ", Content=" + Content + "]";
  }
 
  private String Content;
 
  public TextMsg(String toUserName, String fromUserName, long createTime,
      String msgType, String content) {
    super();
    ToUserName = toUserName;
    FromUserName = fromUserName;
    CreateTime = createTime;
    MsgType = msgType;
    Content = content;
  }
 
  public TextMsg() {
    super();
  }
 
  public String getToUserName() {
    return ToUserName;
  }
 
  public void setToUserName(String toUserName) {
    ToUserName = toUserName;
  }
 
  public String getFromUserName() {
    return FromUserName;
  }
 
  public void setFromUserName(String fromUserName) {
    FromUserName = fromUserName;
  }
 
  public long getCreateTime() {
    return CreateTime;
  }
 
  public void setCreateTime(long createTime) {
    CreateTime = createTime;
  }
 
  public String getMsgType() {
    return MsgType;
  }
 
  public void setMsgType(String msgType) {
    MsgType = msgType;
  }
 
  public String getContent() {
    return Content;
  }
 
  public void setContent(String content) {
    Content = content;
  }
}