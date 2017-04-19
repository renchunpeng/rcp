package com.lflweb.entity.bus;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class BusTrans implements BaseBean {

	// 对账编号;
	private String fileid;

	// 文件名称;
	private String filename;

	// 文件目录;
	private String fileaddr;

	// 创建日期;
	private java.util.Date createdate;

	// 开始日期;
	private java.util.Date busbegindate;

	// 结束日期;
	private java.util.Date busenddate;

	// 交易总条数;
	private String datasum;

	// 交易成功条数;
	private double dataok;

	// 交易退款条数;
	private String datareturn;

	// 交易金额;
	private double dataamt;

	// 交易成功金额;
	private double okamt;

	// 退款金额;
	private double returnamt;

	// 对账人员;
	private String busoperator;

	// 对账日期;
	private java.util.Date busdate;

	// 对账标志;
	private String busflag;

	// 差错条数;
	private int errornum;

	// 差错金额;
	private double erroramt;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BusTrans> item;

	public BusTrans() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		return rtn;
	}

	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((BusTrans)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"对账编号:fileid", "文件名称:filename", "文件目录:fileaddr", "创建日期:createdate", "开始日期:busbegindate", "结束日期:busenddate", "交易总条数:datasum", "交易成功条数:dataok", "交易退款条数:datareturn", "交易金额:dataamt", "交易成功金额:okamt", "退款金额:returnamt", "对账人员:busoperator", "对账日期:busdate", "对账标志:busflag", "差错条数:errornum", "差错金额:erroramt"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.fileid = "";
		this.filename = "";
		this.fileaddr = "";
		this.createdate = ToolUtils.GetMinDate();
		this.busbegindate = ToolUtils.GetMinDate();
		this.busenddate = ToolUtils.GetMinDate();
		this.datasum = "";
		this.dataok = 0;
		this.datareturn = "";
		this.dataamt = 0;
		this.okamt = 0;
		this.returnamt = 0;
		this.busoperator = "";
		this.busdate = ToolUtils.GetMinDate();
		this.busflag = "";
		this.errornum = 0;
		this.erroramt = 0;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid=fileid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename=filename;
	}

	public String getFileaddr() {
		return fileaddr;
	}

	public void setFileaddr(String fileaddr) {
		this.fileaddr=fileaddr;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public java.util.Date getBusbegindate() {
		return busbegindate;
	}

	public void setBusbegindate(java.util.Date busbegindate) {
		this.busbegindate=busbegindate;
	}

	public java.util.Date getBusenddate() {
		return busenddate;
	}

	public void setBusenddate(java.util.Date busenddate) {
		this.busenddate=busenddate;
	}

	public String getDatasum() {
		return datasum;
	}

	public void setDatasum(String datasum) {
		this.datasum=datasum;
	}

	public double getDataok() {
		return dataok;
	}

	public void setDataok(double dataok) {
		this.dataok=dataok;
	}

	public String getDatareturn() {
		return datareturn;
	}

	public void setDatareturn(String datareturn) {
		this.datareturn=datareturn;
	}

	public double getDataamt() {
		return dataamt;
	}

	public void setDataamt(double dataamt) {
		this.dataamt=dataamt;
	}

	public double getOkamt() {
		return okamt;
	}

	public void setOkamt(double okamt) {
		this.okamt=okamt;
	}

	public double getReturnamt() {
		return returnamt;
	}

	public void setReturnamt(double returnamt) {
		this.returnamt=returnamt;
	}

	public String getBusoperator() {
		return busoperator;
	}

	public void setBusoperator(String busoperator) {
		this.busoperator=busoperator;
	}

	public java.util.Date getBusdate() {
		return busdate;
	}

	public void setBusdate(java.util.Date busdate) {
		this.busdate=busdate;
	}

	public String getBusflag() {
		return busflag;
	}

	public void setBusflag(String busflag) {
		this.busflag=busflag;
	}

	public int getErrornum() {
		return errornum;
	}

	public void setErrornum(int errornum) {
		this.errornum=errornum;
	}

	public double getErroramt() {
		return erroramt;
	}

	public void setErroramt(double erroramt) {
		this.erroramt=erroramt;
	}

	public SearchParams getSearch() {
		if (search == null)
			search = new SearchParams();

		return search;
	}

	public void setSearch(SearchParams search) {
		this.search = search;
	}

	public DataDeal getDeal() {
		if (deal == null)
			deal = new DataDeal();

		return deal;
	}

	public void setDeal(DataDeal deal) {
		this.deal = deal;
	}

	public SelectBean<BusTrans> getItem() {
		if (item == null)
			item = new SelectBean<BusTrans>();

		return item;
	}

	public void setItem(SelectBean<BusTrans> item) {
		this.item = item;
	}

}
