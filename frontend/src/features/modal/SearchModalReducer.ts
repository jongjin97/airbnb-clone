import { Reducer } from 'redux';
import { ActionTypes, SearchModalActions } from './SearchModalAction';

interface SearchModalState {
  isOpen: boolean;
}

const initialState: SearchModalState = {
  isOpen: false,
};

const searchModalReducer: Reducer<SearchModalState, SearchModalActions> = (
  state = initialState,
  action
) => {
  switch (action.type) {
    case ActionTypes.OPEN_SEARCH_MODAL:
      return { ...state, isOpen: true };
    case ActionTypes.CLOSE_SEARCH_MODAL:
      return { ...state, isOpen: false };
    default:
      return state;
  }
};

export default searchModalReducer;
