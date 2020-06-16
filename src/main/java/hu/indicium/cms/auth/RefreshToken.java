package hu.indicium.cms.auth;

import hu.indicium.cms.page.Page;
import hu.indicium.cms.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class RefreshToken {
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    private Long id;
    private String token;
    private String jwtId;
    private Date creationDate;
    private boolean invalidated;
    @OneToOne
    @JoinColumn(nullable = false)
    private User user;
}
