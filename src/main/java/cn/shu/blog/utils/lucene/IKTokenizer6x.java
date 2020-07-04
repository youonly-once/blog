package cn.shu.blog.utils.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;


public class IKTokenizer6x extends Tokenizer{
	//ik分词器实现
	private IKSegmenter _IKImplement;
	//词元文本属性
	private final CharTermAttribute termAtt;
	//词元位移属性
	private final OffsetAttribute offsetAtt;
	//词元分类属性
	private final TypeAttribute typeAtt;
	//记录最后一个词元的结束位置
	private int endPosition;
	//构造函数，实现最新的Tokenizer
	public IKTokenizer6x(boolean useSmart){
		super();
		offsetAtt=addAttribute(OffsetAttribute.class);
		termAtt=addAttribute(CharTermAttribute.class);
		typeAtt=addAttribute(TypeAttribute.class);
		_IKImplement=new IKSegmenter(input, useSmart);
	}

	@Override
	public final boolean incrementToken() throws IOException {
		//清除所有的词元属性
		clearAttributes();
		Lexeme nextLexeme=_IKImplement.next();
		if(nextLexeme!=null){
			//将lexeme转成attributes
			termAtt.append(nextLexeme.getLexemeText());
			termAtt.setLength(nextLexeme.getLength());
			offsetAtt.setOffset(nextLexeme.getBeginPosition(), 
					nextLexeme.getEndPosition());
			//记录分词的最后位置
			endPosition=nextLexeme.getEndPosition();
			typeAtt.setType(nextLexeme.getLexemeText());
			return true;//告知还有下个词元
		}
		return false;//告知词元输出完毕
	}
	
	@Override
	public void reset() throws IOException {
		super.reset();
		_IKImplement.reset(input);
	}
	
	@Override
	public final void end(){
		int finalOffset = correctOffset(this.endPosition);
		offsetAtt.setOffset(finalOffset, finalOffset);
	}

}
