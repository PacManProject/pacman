//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/SomeOtherGod

package src.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

//Model class for .../resources/data/maps/*
public class Map {
    String name;
    int pos_x;
    int pos_y;

    Boolean[][] mapData;
    private ArrayList<ArrayList<Integer>> integerMapData;

    Items[][] itemData;
    private ArrayList<ArrayList<Integer>> integerItemData;

    //FIXME: function has to be manually called, as gson doesnt call any constructor to actuate the change,
    //maybe add a custom decoder or a gsonbuilder with a custom workaround
    public void convertMapData(){

        //converts the 1s and 0s list into a boolean ArrayList
        mapData = integerMapData.stream().map(
                mapRow -> mapRow.stream().map(
                        integer -> integer == 1
                ).collect(Collectors.toList())
            ).collect(Collectors.toList())
        //converts the ArrayList to a 2 dimensional Boolean Array (Boolean[][])
        .stream().map(mapRow -> mapRow.toArray(new Boolean[0])).toArray(Boolean[][]::new);

        //converts the integers into an item ArrayList
        itemData = integerItemData.stream().map(
                itemRow -> itemRow.stream().map(
                        //compares every Item (with its itemCode) to the Integer at hand
                        itemCode -> Arrays.stream(Items.values())
                                .filter(item_to_find -> itemCode.equals(item_to_find.itemCode))
                                .findFirst().orElse(Items.none)
                ).collect(Collectors.toList())
            ).collect(Collectors.toList())
        //converts the ArrayList to a 2 dimensional Item Array (Item[][])
        .stream().map(itemRow -> itemRow.toArray(new Items[0])).toArray(Items[][]::new);
    }
}
