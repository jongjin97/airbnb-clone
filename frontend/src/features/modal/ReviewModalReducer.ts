import { Reducer } from 'redux';
import { ActionTypes, ReviewModalActions } from './ReviewModalAction';
interface ReviewModalState {
  isOpen: boolean;
  listingId: string;
}

const initialState: ReviewModalState = {
  isOpen: false,
  listingId: '',
};

const reviewModalReducer: Reducer<ReviewModalState, ReviewModalActions> = (
  state = initialState,
  action
) => {
  switch (action.type) {
    case ActionTypes.OPEN_REVIEW_MODAL:
      return { ...state, isOpen: true, listingId: action.payload };
    case ActionTypes.CLOSE_REVIEW_MODAL:
      return { ...state, isOpen: false, listingId: '' };
    default:
      return state;
  }
};

export default reviewModalReducer;
