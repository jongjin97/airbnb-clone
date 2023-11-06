import { Reducer } from 'redux';
import { ActionTypes, MessageModalActions } from './MessageModalAction';
interface MessageModalState {
  isOpen: boolean;
}

const initialState: MessageModalState = {
  isOpen: false,
};

const messageModalReducer: Reducer<MessageModalState, MessageModalActions> = (
  state = initialState,
  action
) => {
  switch (action.type) {
    case ActionTypes.OPEN_MESSAGE_MODAL:
      return { ...state, isOpen: true };
    case ActionTypes.CLOSE_MESSAGE_MODAL:
      return { ...state, isOpen: false };
    default:
      return state;
  }
};

export default messageModalReducer;
