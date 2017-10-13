package com.hzitoa.email;

/**
 * Created by xianyaoji on 2017/2/27.
 */
public class EmailUtil {
    /**
     * 添加用户发送随机密码到用户邮箱中
     * @param from
     * @param to
     * @param content
     * @return
     */
    public static String  sendEmail(String from,String fromPwd,String to,String content){
        // 设置邮件服务器信息
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.exmail.qq.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        try{
            if(fromPwd!=null && !"".equals(fromPwd)){
                // 邮箱用户名
                mailInfo.setUserName(from);
                // 邮箱密码
                mailInfo.setPassword(fromPwd);
                //发件人邮箱
                mailInfo.setFromAddress(from);
            } else{
                // 邮箱用户名
                mailInfo.setUserName("meiyang@hzitxx.com");
                // 邮箱密码
                mailInfo.setPassword("MeiYang19941114");
                //发件人邮箱
                mailInfo.setFromAddress("meiyang@hzitxx.com");
            }

            // 收件人邮箱
            mailInfo.setToAddress(to);
            // 邮件标题
            mailInfo.setSubject("合众艾特员工管理系统邮件");
            // 邮件内容
            StringBuffer buffer = new StringBuffer();
            //buffer.append("合众艾特咨询系统登录密码，建议登录后立即修改面密码!<br>");
            buffer.append(content);
            mailInfo.setContent(buffer.toString());
            // 发送邮件
            SimpleMailSender sms = new SimpleMailSender();
            // 发送文体格式
          //  long l=System.currentTimeMillis();
             sms.sendTextMail(mailInfo);
           // System.out.println(System.currentTimeMillis()-l+"毫秒");
            // 发送html格式
        //    SimpleMailSender.sendHtmlMail(mailInfo);
            return  "成功";
        }catch ( Exception e){
            return  "失败";
        }

    }
}
