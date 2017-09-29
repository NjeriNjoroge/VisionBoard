package com.example.gnjoroge.visionboard.util;

/**
 * Created by gnjoroge on 9/29/17.
 */

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
