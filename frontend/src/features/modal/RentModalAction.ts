export enum ActionTypes {
  OPEN_RENT_MODAL = 'OPEN_RENT_MODAL',
  CLOSE_RENT_MODAL = 'CLOSE_RENT_MODAL',
}

interface OpenRentModalAction {
  type: ActionTypes.OPEN_RENT_MODAL;
}

interface CloseRentModalAction {
  type: ActionTypes.CLOSE_RENT_MODAL;
}

export type RentModalActions = OpenRentModalAction | CloseRentModalAction;

export const openRentModal = (): RentModalActions => ({
  type: ActionTypes.OPEN_RENT_MODAL,
});

export const closeRentModal = (): RentModalActions => ({
  type: ActionTypes.CLOSE_RENT_MODAL,
});
