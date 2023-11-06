export enum ActionTypes {
  OPEN_LOGIN_MODAL = 'OPEN_LOGIN_MODAL',
  CLOSE_LOGIN_MODAL = 'CLOSE_LOGIN_MODAL',
  OPEN_MESSAGE_MODAL = 'OPEN_MESSAGE_MODAL',
}

interface OpenLoginModalAction {
  type: ActionTypes.OPEN_LOGIN_MODAL;
}

interface CloseLoginModalAction {
  type: ActionTypes.CLOSE_LOGIN_MODAL;
}

export type LoginModalActions = OpenLoginModalAction | CloseLoginModalAction;

export const openLoginModal = (): LoginModalActions => ({
  type: ActionTypes.OPEN_LOGIN_MODAL,
});

export const closeLoginModal = (): LoginModalActions => ({
  type: ActionTypes.CLOSE_LOGIN_MODAL,
});
