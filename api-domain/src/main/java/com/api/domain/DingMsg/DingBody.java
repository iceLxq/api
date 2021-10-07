package com.api.domain.DingMsg;

/**
 * Created by 李显琪 on 2021/10/7.
 */
public class DingBody {

    private String msgtype;

    private Text text = new Text();


    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }



    public class Text {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
