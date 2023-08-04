package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.RegionPK;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(RegionPK.class)
@Table(name = "region")
public class Region {

    @Id
    @Column(name = "supDistrict", length = 200, nullable = false)
    private String supDistrict;

    @Id
    @Column(name = "district", length = 200, nullable = false)
    private String district;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "supDistrict", referencedColumnName = "supDistrict"),
            @JoinColumn(name = "district", referencedColumnName = "district")
    })
    private Stamp stamp;

    @OneToMany(mappedBy = "region")
    private List<Landmark> landmarkList;

    @Builder
    public Region(String supDistrict, String district){
        this.supDistrict = supDistrict;
        this.district =district;
    }

}