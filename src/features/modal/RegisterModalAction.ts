export enum ActionTypes {
    OPEN_REGISTER_MODAL = 'OPEN_REGISTER_MODAL',
    CLOSE_REGISTER_MODAL = 'CLOSE_REGISTER_MODAL',
  }
  
  interface OpenRegisterModalAction {
    type: ActionTypes.OPEN_REGISTER_MODAL;
  }
  
  interface CloseRegisterModalAction {
    type: ActionTypes.CLOSE_REGISTER_MODAL;
  }
  
  export type RegisterModalActions = OpenRegisterModalAction | CloseRegisterModalAction;
  
  export const openRegisterModal = (): RegisterModalActions => ({
    type: ActionTypes.OPEN_REGISTER_MODAL,
  });
  
  export const closeRegisterModal = (): RegisterModalActions => ({
    type: ActionTypes.CLOSE_REGISTER_MODAL,
  });
  