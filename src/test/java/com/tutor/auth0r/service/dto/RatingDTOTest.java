package com.tutor.auth0r.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.tutor.auth0r.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RatingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RatingDTO.class);
        RatingDTO ratingDTO1 = new RatingDTO();
        ratingDTO1.setId(1L);
        RatingDTO ratingDTO2 = new RatingDTO();
        assertThat(ratingDTO1).isNotEqualTo(ratingDTO2);
        ratingDTO2.setId(ratingDTO1.getId());
        assertThat(ratingDTO1).isEqualTo(ratingDTO2);
        ratingDTO2.setId(2L);
        assertThat(ratingDTO1).isNotEqualTo(ratingDTO2);
        ratingDTO1.setId(null);
        assertThat(ratingDTO1).isNotEqualTo(ratingDTO2);
    }
}
