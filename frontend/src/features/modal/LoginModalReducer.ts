import { Reducer } from "redux";
import { ActionTypes, LoginModalActions } from './LoginModalAction';
interface LoginModalState {
    isOpen: boolean;
  }
  
  const initialState: LoginModalState = {
    isOpen: false,
  };
  
  const loginModalReducer: Reducer<LoginModalState, LoginModalActions> = (
    state = initialState,
    action
  ) => {
    switch (action.type) {
      case ActionTypes.OPEN_LOGIN_MODAL:
        return { ...state, isOpen: true };
      case ActionTypes.CLOSE_LOGIN_MODAL:
        return { ...state, isOpen: false };
      default:
        return state;
     }
  };

  export default loginModalReducer;