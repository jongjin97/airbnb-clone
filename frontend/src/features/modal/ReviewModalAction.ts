export enum ActionTypes {
    OPEN_REVIEW_MODAL = 'OPEN_REVIEW_MODAL',
    CLOSE_REVIEW_MODAL = 'CLOSE_REVIEW_MODAL',
}
  
  interface OpenReviewModalAction {
    type: ActionTypes.OPEN_REVIEW_MODAL;
    payload: string;
  }
  
  interface CloseReviewModalAction {
    type: ActionTypes.CLOSE_REVIEW_MODAL;
  }
  
  export type ReviewModalActions = OpenReviewModalAction | CloseReviewModalAction;
  
  export const openReviewModal = (data: string): ReviewModalActions => ({
    type: ActionTypes.OPEN_REVIEW_MODAL,
    payload: data,
  });
  
  export const closeReviewModal = (): ReviewModalActions => ({
    type: ActionTypes.CLOSE_REVIEW_MODAL,
  });
  