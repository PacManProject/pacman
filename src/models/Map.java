//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/SomeOtherGod

package src.models;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
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

    public Map(String name, int pos_x, int pos_y, int[][] item) {
        Gson gson = new Gson();
        this.name = name;
        this.pos_x = pos_x;
        this.pos_y = pos_y;

        ArrayList<Integer> tempList;
        ArrayList<ArrayList<Integer>> tempListList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < item.length; i++) {
            tempList = new ArrayList<Integer>();
            for (int j = 0; j < item[0].length; j++) {
                tempList.add(item[i][j]);
            }
            tempListList.add(tempList);
        }
        this.integerItemData = tempListList;

        tempListList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < item.length; i++) {
            tempList = new ArrayList<Integer>();
            for (int j = 0; j < item[0].length; j++) {
                if (item[i][j] == 0) {
                    tempList.add(0);
                } else {
                    tempList.add(1);
                }
            }
            tempListList.add(tempList);
        }
        this.integerMapData = tempListList;

        try (Writer writer = new FileWriter(String.valueOf(Paths.get(String.valueOf(Paths.get(System.getProperty("user.dir"), "resources", "data", "maps")), this.name + ".json")))) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
