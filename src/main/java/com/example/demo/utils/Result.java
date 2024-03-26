package com.example.demo.utils;

import java.util.Set;
import java.io.Serializable;

/**
 * 接口返回结果封装
 * @author w3441
 *
 * @param <T>
 */
public class Result<T> implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2454344986046084109L;

	/**
	 * @Fields code : TODO(状态码)
	 */
	private int code;

	/**
	 * @Fields msg : TODO(提示信息)
	 */
	private String msg;

	/**
	 * @Fields data : TODO(具体的数据)
	 */
	private T data;
	
	
	private Set<String> ignoreSet;


	public Result() {
		super();
	}

	/**
	 * <p>Title </p>
	 * <p>Description </p>
	 * @param data
	*/
	private Result(T data) {
		this.code = ResultEnum.SUCCESS.getCode();
		this.msg = ResultEnum.SUCCESS.getMsg();
		this.data = data;
	}

	/**
	 * <p>Title </p>
	 * <p>Description </p>
	 * @param msg
	*/
	private Result(String msg) {
		this.code = ResultEnum.ERROR.getCode();
		this.data = null;
		this.msg = msg;
	}

	/**
	 * <p>Title </p>
	 * <p>Description </p>
	 * @param resultEnum
	*/
	private Result(ResultEnum resultEnum) {
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}

	/**
	 * <p>Title </p>
	 * <p>Description </p>
	 * @param code
	 * @param msg
	*/
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * <p>Title </p>
	 * <p>Description </p>
	 * @param code
	 * @param msg
	 * @param data
	*/
	private Result(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


	/**
	 * @return code
	*/
	public int getCode() {
		return code;
	}

	/**
	 * @param code 要设置的 code
	*/
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return msg
	*/
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg 要设置的 msg
	*/
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return data
	*/
	public T getData() {
		return data;
	}

	/**
	 * @param data 要设置的 data
	*/
	public void setData(T data) {
		this.data = data;
	}
	
	
	public Set<String> getIgnoreSet() {
		return ignoreSet;
	}

	public void setIgnoreSet(Set<String> ignoreSet) {
		this.ignoreSet = ignoreSet;
	}

	/**
	 * @Title success
	 * @Description TODO(操作成功)
	 * @param data
	 * @return Result<T> 返回类型
	 */
	public static <T> Result<T> success(T data) {
		return new Result<>(data);
	}

	/**
	 * @Title build
	 * @Description TODO(根据返回的状态对象， 构建返回结果)
	 * @param resultEnum
	 * @return Result<T> 返回类型
	*/
	public static <T> Result<T> build(ResultEnum resultEnum) {
		return new Result<>(resultEnum);
	}

	/**
	 * @Title build
	 * @Description TODO(根据 code 和 msg 构建返回结果)
	 * @param code
	 * @param msg
	 * @return Result<T> 返回类型
	*/
	public static <T> Result<T> build(int code, String msg) {
		return new Result<T>(code, msg);
	}

	/**
	 * @Title build
	 * @Description TODO(根据 code、 msg、data 构建返回结果)
	 * @param code
	 * @param msg
	 * @param data
	 * @return Result<T> 返回类型
	*/
	public static <T> Result<T> build(int code, String msg, T data) {
		return new Result<T>(code, msg, data);
	}

	/**
	 * @Title error
	 * @Description TODO(操作失败)
	 * @param codeMsg
	 * @return Result<T> 返回类型
	*/
	public static <T> Result<T> error(String codeMsg) {
		return new Result<>(codeMsg);
	}

	/**
	 * @Title error
	 * @Description TODO(操作失败)
	 * @param data
	 * @return Result<T> 返回类型
	*/
	public static <T> Result<T> error(T data) {
		return new Result<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), data);
	}
	
	/**
	 * @ClassName ResultEnum
	 * @Description TODO(枚举)
	 * @author 王佳男
	 * @date 2020年10月23日 上午11:48:49
	 *
	*/
	public enum ResultEnum {

		SUCCESS(200, "操作成功"), 
		ERROR(400, "系统异常，请重试！"),
		FEIGNERROR(601,"服务调用异常"),
		SELECTNULL(210,"查询结果为空"),
		DATAREFUSH(602,"数据异常，请刷新重试"),
		NOPERMISSION(603,"无权限")
		;

		/**
		 * @Fields code : TODO(状态码)
		*/
		private int code;

		/**
		 * @Fields msg : TODO(提示信息)
		*/
		private String msg;

		/**
		 * <p>Title </p>
		 * <p>Description </p>
		 * @param code
		 * @param msg
		*/
		ResultEnum(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		/**
		 * @return code
		*/
		public int getCode() {
			return code;
		}

		/**
		 * @param code 要设置的 code
		*/
		public void setCode(int code) {
			this.code = code;
		}

		/**
		 * @return msg
		*/
		public String getMsg() {
			return msg;
		}

		/**
		 * @param msg 要设置的 msg
		*/
		public void setMsg(String msg) {
			this.msg = msg;
		}

		
	}
}
