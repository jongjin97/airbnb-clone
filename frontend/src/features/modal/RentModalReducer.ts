import { Reducer } from 'redux';
import { ActionTypes, RentModalActions } from './RentModalAction';

interface RentModalState {
  isOpen: boolean;
}

const initialState: RentModalState = {
  isOpen: false,
};

const rentModalReducer: Reducer<RentModalState, RentModalActions> = (
  state = initialState,
  action
) => {
  switch (action.type) {
    case ActionTypes.OPEN_RENT_MODAL:
      return { ...state, isOpen: true };
    case ActionTypes.CLOSE_RENT_MODAL:
      return { ...state, isOpen: false };
    default:
      return state;
  }
};

export default rentModalReducer;
