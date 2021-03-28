import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  private openedDialogCount = 0;

  constructor() {
  }

  dialogOpened() {
    this.openedDialogCount++;

    const htmlElement = document.querySelector('html')! as HTMLElement;
    if (!htmlElement.classList.contains('is-clipped')) {
      htmlElement.classList.add('is-clipped');
    }
  }

  dialogClosed() {
    this.openedDialogCount--;

    const htmlElement = document.querySelector('html')! as HTMLElement;
    if (htmlElement.classList.contains('is-clipped')) {
      htmlElement.classList.remove('is-clipped');
    }
  }

}
