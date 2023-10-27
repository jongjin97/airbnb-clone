import { Reducer } from "redux";
import { ActionTypes, ReviewModalActions } from "./ReviewModalAction";
interface ReviewModalState {
    isOpen: boolean;
  }
  
  const initialState: ReviewModalState = {
    isOpen: false,
  };
  
  const reviewModalReducer: Reducer<ReviewModalState, ReviewModalActions> = (
    state = initialState,
    action
  ) => {
    switch (action.type) {
      case ActionTypes.OPEN_REVIEW_MODAL:
        return { ...state, isOpen: true };
      case ActionTypes.CLOSE_REVIEW_MODAL:
        return { ...state, isOpen: false };
      default:
        return state;
     }
  };

  export default reviewModalReducer;