import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './hire-tutor-my-suffix.reducer';

export const HireTutorMySuffix = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const hireTutorList = useAppSelector(state => state.hireTutor.entities);
  const loading = useAppSelector(state => state.hireTutor.loading);

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
      <h2 id="hire-tutor-my-suffix-heading" data-cy="HireTutorHeading">
        <Translate contentKey="projectApp.hireTutor.home.title">Hire Tutors</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="projectApp.hireTutor.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/hire-tutor-my-suffix/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="projectApp.hireTutor.home.createLabel">Create new Hire Tutor</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {hireTutorList && hireTutorList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="projectApp.hireTutor.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('timeHire')}>
                  <Translate contentKey="projectApp.hireTutor.timeHire">Time Hire</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('timeHire')} />
                </th>
                <th className="hand" onClick={sort('totalPrice')}>
                  <Translate contentKey="projectApp.hireTutor.totalPrice">Total Price</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalPrice')} />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="projectApp.hireTutor.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('status')} />
                </th>
                <th>
                  <Translate contentKey="projectApp.hireTutor.appUser">App User</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="projectApp.hireTutor.tutor">Tutor</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hireTutorList.map((hireTutor, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/hire-tutor-my-suffix/${hireTutor.id}`} color="link" size="sm">
                      {hireTutor.id}
                    </Button>
                  </td>
                  <td>{hireTutor.timeHire}</td>
                  <td>{hireTutor.totalPrice}</td>
                  <td>
                    <Translate contentKey={`projectApp.HireStatus.${hireTutor.status}`} />
                  </td>
                  <td>{hireTutor.appUser ? <Link to={`/app-user-my-suffix/${hireTutor.appUser.id}`}>{hireTutor.appUser.id}</Link> : ''}</td>
                  <td>{hireTutor.tutor ? <Link to={`/tutor-my-suffix/${hireTutor.tutor.id}`}>{hireTutor.tutor.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/hire-tutor-my-suffix/${hireTutor.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/hire-tutor-my-suffix/${hireTutor.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/hire-tutor-my-suffix/${hireTutor.id}/delete`)}
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
              <Translate contentKey="projectApp.hireTutor.home.notFound">No Hire Tutors found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default HireTutorMySuffix;
