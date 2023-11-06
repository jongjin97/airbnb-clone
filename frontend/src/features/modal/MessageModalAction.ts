export enum ActionTypes {
  OPEN_MESSAGE_MODAL = 'OPEN_MESSAGE_MODAL',
  CLOSE_MESSAGE_MODAL = 'CLOSE_MESSAGE_MODAL',
}

interface OpenMessageModalAction {
  type: ActionTypes.OPEN_MESSAGE_MODAL;
}

interface CloseMessageModalAction {
  type: ActionTypes.CLOSE_MESSAGE_MODAL;
}

export type MessageModalActions =
  | OpenMessageModalAction
  | CloseMessageModalAction;

export const openMessageModal = (): MessageModalActions => ({
  type: ActionTypes.OPEN_MESSAGE_MODAL,
});

export const closeMessageModal = (): MessageModalActions => ({
  type: ActionTypes.CLOSE_MESSAGE_MODAL,
});
