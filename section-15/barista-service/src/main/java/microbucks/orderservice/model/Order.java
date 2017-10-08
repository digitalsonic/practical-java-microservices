package microbucks.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String customer;
    private String barista;
    private String content;
    private Integer state;
    private String price; // 简单期间使用String，真实场景中请使用JodaMoney
    private Date createTime;
    private Date modifyTime;
}
