package bitcamp.project1.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static int nextSeqNo = 1;
    private int no;
    private String type;
    private Date createdDate;
    private String category;
    private String content;
    private int amount; // 타입 변경

    public Transaction() {
    }

    public Transaction(int no) {
        this.no = no;
    }

    public static int getNextSeqNo() {
        return nextSeqNo++;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreatedDate(String createdDateStr) throws Exception {
        this.createdDate = dateFormat.parse(createdDateStr);
    }

    public String getCreatedDateFormatted() {
        return dateFormat.format(createdDate);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaction that = (Transaction) obj;
        return no == that.no;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(no);
    }

}
