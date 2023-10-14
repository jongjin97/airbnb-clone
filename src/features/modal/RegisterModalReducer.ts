import { Reducer } from "redux";
import { ActionTypes, RegisterModalActions } from './RegisterModalAction';
interface RegisterModalState {
    isOpen: boolean;
  }
  
  const initialState: RegisterModalState = {
    isOpen: false,
  };
  
  const registerModalReducer: Reducer<RegisterModalState, RegisterModalActions> = (
    state = initialState,
    action
  ) => {
    console.log(state);
    switch (action.type) {
      case ActionTypes.OPEN_REGISTER_MODAL:
        return { ...state, isOpen: true };
      case ActionTypes.CLOSE_REGISTER_MODAL:
        return { ...state, isOpen: false };
      default:
        return state;
     }
  };

  export default registerModalReducer;