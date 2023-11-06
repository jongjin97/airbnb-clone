import { HIDE_ERROR, SET_ERROR } from './errorActions';

interface ErrorState {
  error: any;
  isOpen: boolean;
}

const initialState: ErrorState = {
  error: null,
  isOpen: false,
};
interface Action {
  type: string;
  error?: any;
}
const errorReducer = (state = initialState, action: Action) => {
  switch (action.type) {
    case SET_ERROR:
      return {
        error: action.error,
        isOpen: true,
      };
    case HIDE_ERROR:
      return {
        error: null,
        isOpen: false,
      };
    default:
      return {
        error: state.error,
        isOpen: initialState.isOpen,
      };
  }
};
export default errorReducer;
