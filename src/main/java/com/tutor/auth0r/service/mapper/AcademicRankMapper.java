package com.tutor.auth0r.service.mapper;

import com.tutor.auth0r.domain.AcademicRank;
import com.tutor.auth0r.domain.Media;
import com.tutor.auth0r.domain.UserVerify;
import com.tutor.auth0r.service.dto.AcademicRankDTO;
import com.tutor.auth0r.service.dto.CustomDTO.RankwithImageDTO;
import com.tutor.auth0r.service.dto.MediaDTO;
import com.tutor.auth0r.service.dto.UserVerifyDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AcademicRank} and its DTO {@link AcademicRankDTO}.
 */
@Mapper(componentModel = "spring")
public interface AcademicRankMapper extends EntityMapper<AcademicRankDTO, AcademicRank> {
    @Mapping(target = "media", source = "media", qualifiedByName = "mediaId")
    @Mapping(target = "userVerify", source = "userVerify", qualifiedByName = "userVerifyId")
    AcademicRankDTO toDto(AcademicRank s);

    @Named("mediaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MediaDTO toDtoMediaId(Media media);

    @Named("userVerifyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserVerifyDTO toDtoUserVerifyId(UserVerify userVerify);

    @Mappings({ @Mapping(source = "type", target = "rank"), @Mapping(source = "media.url", target = "url") })
    RankwithImageDTO toRankwithImageDTO(AcademicRank userVerify);

    Set<RankwithImageDTO> toRankwithImageDTOs(Set<AcademicRank> academicRanks);

    @Mappings(
        {
            @Mapping(source = "rank", target = "type"),
            @Mapping(source = "url", target = "media.url"),
            @Mapping(target = "userVerify", ignore = true),
        }
    )
    AcademicRank toAcademicRank(RankwithImageDTO rankwithImageDTO);

    Set<AcademicRank> toAcademicRanks(Set<RankwithImageDTO> rankwithImageDTOs);
}
