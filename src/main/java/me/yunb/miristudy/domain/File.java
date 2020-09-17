package me.yunb.miristudy.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor // JPA는 기본 생성자 반드시 필요
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String key;

    @Column
    private Long size;

    public File(String key, long size) {
        this.key = key;
        this.size = size;
    }
}
