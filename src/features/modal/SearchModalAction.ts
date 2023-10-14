export enum ActionTypes {
    OPEN_SEARCH_MODAL = 'OPEN_SEARCH_MODAL',
    CLOSE_SEARCH_MODAL = 'CLOSE_SEARCH_MODAL',
  }
  
  interface OpenSearchModalAction {
    type: ActionTypes.OPEN_SEARCH_MODAL;
  }
  
  interface CloseSearchModalAction {
    type: ActionTypes.CLOSE_SEARCH_MODAL;
  }
  
  export type SearchModalActions = OpenSearchModalAction | CloseSearchModalAction;
  
  export const openSearchModal = (): SearchModalActions => ({
    type: ActionTypes.OPEN_SEARCH_MODAL,
  });
  
  export const closeSearchModal = (): SearchModalActions => ({
    type: ActionTypes.CLOSE_SEARCH_MODAL,
  });
  