package com.tutor.auth0r.service;

import com.tutor.auth0r.service.dto.ReportDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tutor.auth0r.domain.Report}.
 */
public interface ReportService {
    /**
     * Save a report.
     *
     * @param reportDTO the entity to save.
     * @return the persisted entity.
     */
    ReportDTO save(ReportDTO reportDTO);

    /**
     * Updates a report.
     *
     * @param reportDTO the entity to update.
     * @return the persisted entity.
     */
    ReportDTO update(ReportDTO reportDTO);

    /**
     * Partially updates a report.
     *
     * @param reportDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReportDTO> partialUpdate(ReportDTO reportDTO);

    /**
     * Get all the reports.
     *
     * @return the list of entities.
     */
    List<ReportDTO> findAll();

    /**
     * Get the "id" report.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportDTO> findOne(Long id);

    /**
     * Delete the "id" report.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
