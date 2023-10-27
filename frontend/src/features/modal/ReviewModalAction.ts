export enum ActionTypes {
    OPEN_REVIEW_MODAL = 'OPEN_REVIEW_MODAL',
    CLOSE_REVIEW_MODAL = 'CLOSE_REVIEW_MODAL',
}
  
  interface OpenReviewModalAction {
    type: ActionTypes.OPEN_REVIEW_MODAL;
  }
  
  interface CloseReviewModalAction {
    type: ActionTypes.CLOSE_REVIEW_MODAL;
  }
  
  export type ReviewModalActions = OpenReviewModalAction | CloseReviewModalAction;
  
  export const openReviewModal = (): ReviewModalActions => ({
    type: ActionTypes.OPEN_REVIEW_MODAL,
  });
  
  export const closeReviewModal = (): ReviewModalActions => ({
    type: ActionTypes.CLOSE_REVIEW_MODAL,
  });
  