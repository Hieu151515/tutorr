import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './tutor-my-suffix.reducer';

export const TutorMySuffix = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const tutorList = useAppSelector(state => state.tutor.entities);
  const loading = useAppSelector(state => state.tutor.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="tutor-my-suffix-heading" data-cy="TutorHeading">
        <Translate contentKey="projectApp.tutor.home.title">Tutors</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="projectApp.tutor.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/tutor-my-suffix/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="projectApp.tutor.home.createLabel">Create new Tutor</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tutorList && tutorList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="projectApp.tutor.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('recommend')}>
                  <Translate contentKey="projectApp.tutor.recommend">Recommend</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('recommend')} />
                </th>
                <th className="hand" onClick={sort('price')}>
                  <Translate contentKey="projectApp.tutor.price">Price</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('price')} />
                </th>
                <th className="hand" onClick={sort('tuDevice')}>
                  <Translate contentKey="projectApp.tutor.tuDevice">Tu Device</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tuDevice')} />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="projectApp.tutor.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('status')} />
                </th>
                <th className="hand" onClick={sort('followerCount')}>
                  <Translate contentKey="projectApp.tutor.followerCount">Follower Count</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('followerCount')} />
                </th>
                <th className="hand" onClick={sort('averageRating')}>
                  <Translate contentKey="projectApp.tutor.averageRating">Average Rating</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('averageRating')} />
                </th>
                <th>
                  <Translate contentKey="projectApp.tutor.tutorDetails">Tutor Details</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tutorList.map((tutor, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/tutor-my-suffix/${tutor.id}`} color="link" size="sm">
                      {tutor.id}
                    </Button>
                  </td>
                  <td>{tutor.recommend ? 'true' : 'false'}</td>
                  <td>{tutor.price}</td>
                  <td>
                    <Translate contentKey={`projectApp.Devide.${tutor.tuDevice}`} />
                  </td>
                  <td>
                    <Translate contentKey={`projectApp.TuStatus.${tutor.status}`} />
                  </td>
                  <td>{tutor.followerCount}</td>
                  <td>{tutor.averageRating}</td>
                  <td>
                    {tutor.tutorDetails ? (
                      <Link to={`/tutor-details-my-suffix/${tutor.tutorDetails.id}`}>{tutor.tutorDetails.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/tutor-my-suffix/${tutor.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/tutor-my-suffix/${tutor.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/tutor-my-suffix/${tutor.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="projectApp.tutor.home.notFound">No Tutors found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default TutorMySuffix;
