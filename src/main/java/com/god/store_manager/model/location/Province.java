package com.god.store_manager.model.location;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column()
    private String provinceName;
    @OneToMany(mappedBy = "province",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<District>districts;
}
