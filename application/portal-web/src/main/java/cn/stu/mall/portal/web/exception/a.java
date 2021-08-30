package cn.stu.mall.portal.web.exception;

public class a extends Exception{
    @Override
    public void printStackTrace() {
        System.out.println("自定义异常，打印错误信息");
    }

    public a(String message) {
        super(message);
    }
}
