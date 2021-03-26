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
		this(false);
	}

	@Override
	protected TokenStreamComponents createComponents(String filedName) {
		Tokenizer ikTokenizer6x=new IKTokenizer6x(this.useSmart);
		return new TokenStreamComponents(ikTokenizer6x);
	}
	public IKAnalyzer6x(boolean useSmart){
		super();
		this.useSmart=useSmart;
	}
	
}
