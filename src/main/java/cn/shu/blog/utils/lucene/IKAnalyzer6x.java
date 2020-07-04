package cn.shu.blog.utils.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

public class IKAnalyzer6x extends Analyzer{
	private boolean useSmart;
	public boolean useSmart(){
		return useSmart;
	}
	public void setUseSmart(boolean useSmart){
		this.useSmart=useSmart;
	}
	public IKAnalyzer6x(){
		this(false);//IK分词器lucene analyzer接口实现类，默认细粒度切分算法
	}
	//重写最新版本createComponents；重载analyzer接口，构造分词组件
	@Override
	protected TokenStreamComponents createComponents(String filedName) {
		Tokenizer _IKTokenizer=new IKTokenizer6x(this.useSmart);
		return new TokenStreamComponents(_IKTokenizer);
	}
	public IKAnalyzer6x(boolean useSmart){
		super();
		this.useSmart=useSmart;
	}
	
}
