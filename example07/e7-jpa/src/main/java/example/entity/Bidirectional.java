package example.entity;

/**
 * 解决双向关联递归引用
 * @author OracleGao
 *
 */
public interface Bidirectional {
	/**
	 * 调用与之关联实体的releaseBidirectionalRelationship方法打破双相关联
	 */
	public void breakBidirectionalRelationship();
	
	/**
	 * 释放双相关联关系，将实体引用值设为null
	 */
	void releaseBidirectionalRelationship();
}
