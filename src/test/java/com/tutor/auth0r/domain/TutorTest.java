package com.tutor.auth0r.domain;

import static com.tutor.auth0r.domain.AppUserTestSamples.*;
import static com.tutor.auth0r.domain.HireTutorTestSamples.*;
import static com.tutor.auth0r.domain.HiringHoursTestSamples.*;
import static com.tutor.auth0r.domain.RatingTestSamples.*;
import static com.tutor.auth0r.domain.ReportTestSamples.*;
import static com.tutor.auth0r.domain.TutorDetailsTestSamples.*;
import static com.tutor.auth0r.domain.TutorTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.tutor.auth0r.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TutorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tutor.class);
        Tutor tutor1 = getTutorSample1();
        Tutor tutor2 = new Tutor();
        assertThat(tutor1).isNotEqualTo(tutor2);

        tutor2.setId(tutor1.getId());
        assertThat(tutor1).isEqualTo(tutor2);

        tutor2 = getTutorSample2();
        assertThat(tutor1).isNotEqualTo(tutor2);
    }

    @Test
    void tutorDetailsTest() {
        Tutor tutor = getTutorRandomSampleGenerator();
        TutorDetails tutorDetailsBack = getTutorDetailsRandomSampleGenerator();

        tutor.setTutorDetails(tutorDetailsBack);
        assertThat(tutor.getTutorDetails()).isEqualTo(tutorDetailsBack);

        tutor.tutorDetails(null);
        assertThat(tutor.getTutorDetails()).isNull();
    }

    @Test
    void hireTutorTest() {
        Tutor tutor = getTutorRandomSampleGenerator();
        HireTutor hireTutorBack = getHireTutorRandomSampleGenerator();

        tutor.addHireTutor(hireTutorBack);
        assertThat(tutor.getHireTutors()).containsOnly(hireTutorBack);
        assertThat(hireTutorBack.getTutor()).isEqualTo(tutor);

        tutor.removeHireTutor(hireTutorBack);
        assertThat(tutor.getHireTutors()).doesNotContain(hireTutorBack);
        assertThat(hireTutorBack.getTutor()).isNull();

        tutor.hireTutors(new HashSet<>(Set.of(hireTutorBack)));
        assertThat(tutor.getHireTutors()).containsOnly(hireTutorBack);
        assertThat(hireTutorBack.getTutor()).isEqualTo(tutor);

        tutor.setHireTutors(new HashSet<>());
        assertThat(tutor.getHireTutors()).doesNotContain(hireTutorBack);
        assertThat(hireTutorBack.getTutor()).isNull();
    }

    @Test
    void hiringHoursTest() {
        Tutor tutor = getTutorRandomSampleGenerator();
        HiringHours hiringHoursBack = getHiringHoursRandomSampleGenerator();

        tutor.addHiringHours(hiringHoursBack);
        assertThat(tutor.getHiringHours()).containsOnly(hiringHoursBack);
        assertThat(hiringHoursBack.getTutor()).isEqualTo(tutor);

        tutor.removeHiringHours(hiringHoursBack);
        assertThat(tutor.getHiringHours()).doesNotContain(hiringHoursBack);
        assertThat(hiringHoursBack.getTutor()).isNull();

        tutor.hiringHours(new HashSet<>(Set.of(hiringHoursBack)));
        assertThat(tutor.getHiringHours()).containsOnly(hiringHoursBack);
        assertThat(hiringHoursBack.getTutor()).isEqualTo(tutor);

        tutor.setHiringHours(new HashSet<>());
        assertThat(tutor.getHiringHours()).doesNotContain(hiringHoursBack);
        assertThat(hiringHoursBack.getTutor()).isNull();
    }

    @Test
    void reportTest() {
        Tutor tutor = getTutorRandomSampleGenerator();
        Report reportBack = getReportRandomSampleGenerator();

        tutor.addReport(reportBack);
        assertThat(tutor.getReports()).containsOnly(reportBack);
        assertThat(reportBack.getTutor()).isEqualTo(tutor);

        tutor.removeReport(reportBack);
        assertThat(tutor.getReports()).doesNotContain(reportBack);
        assertThat(reportBack.getTutor()).isNull();

        tutor.reports(new HashSet<>(Set.of(reportBack)));
        assertThat(tutor.getReports()).containsOnly(reportBack);
        assertThat(reportBack.getTutor()).isEqualTo(tutor);

        tutor.setReports(new HashSet<>());
        assertThat(tutor.getReports()).doesNotContain(reportBack);
        assertThat(reportBack.getTutor()).isNull();
    }

    @Test
    void ratingTest() {
        Tutor tutor = getTutorRandomSampleGenerator();
        Rating ratingBack = getRatingRandomSampleGenerator();

        tutor.addRating(ratingBack);
        assertThat(tutor.getRatings()).containsOnly(ratingBack);
        assertThat(ratingBack.getTutor()).isEqualTo(tutor);

        tutor.removeRating(ratingBack);
        assertThat(tutor.getRatings()).doesNotContain(ratingBack);
        assertThat(ratingBack.getTutor()).isNull();

        tutor.ratings(new HashSet<>(Set.of(ratingBack)));
        assertThat(tutor.getRatings()).containsOnly(ratingBack);
        assertThat(ratingBack.getTutor()).isEqualTo(tutor);

        tutor.setRatings(new HashSet<>());
        assertThat(tutor.getRatings()).doesNotContain(ratingBack);
        assertThat(ratingBack.getTutor()).isNull();
    }

    @Test
    void appUserTest() {
        Tutor tutor = getTutorRandomSampleGenerator();
        AppUser appUserBack = getAppUserRandomSampleGenerator();

        tutor.setAppUser(appUserBack);
        assertThat(tutor.getAppUser()).isEqualTo(appUserBack);
        assertThat(appUserBack.getTutor()).isEqualTo(tutor);

        tutor.appUser(null);
        assertThat(tutor.getAppUser()).isNull();
        assertThat(appUserBack.getTutor()).isNull();
    }
}
