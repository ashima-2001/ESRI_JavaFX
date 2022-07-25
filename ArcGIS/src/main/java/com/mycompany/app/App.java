/**
 * Copyright 2019 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.mycompany.app;


import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private MapView mapView;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("My Map App");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();

        // create a JavaFX scene with a stack pane as the root node and add it to the scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        // Note: it is not best practice to store API keys in source code.
        String yourApiKey = "AAPK1debc8eb097a43c7b3b49edbbb337b17kKe7Z8iTcHktFOMm8AWJwgs40HLgYuX2WCqtZ64PxB5JX4SSIvYdgPDplLw-Y5My";
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);

        // create a MapView to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);


        // create an ArcGISMap with an imagery basemap
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);

        // display the map by setting the map on the map view
        mapView.setMap(map);
        mapView.setViewpoint(new Viewpoint(30.35206725584051, 76.3779762315455, 6000000)); //30.35206725584051, 76.3779762315455   34.02700, -118.80543

        // create a graphics overlay and add it to the map view
        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        // create a point geometry with a location and spatial reference
        Point point = new Point(76.3779762315455, 30.35206725584051, SpatialReferences.getWgs84());
        // create an opaque orange (0xFFFF5733) point symbol with a blue (0xFF0063FF) outline symbol
        SimpleMarkerSymbol simpleMarkerSymbol =
                new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF5733, 10);
        SimpleLineSymbol blueOutlineSymbol =
                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0063FF, 2);

        simpleMarkerSymbol.setOutline(blueOutlineSymbol);
        Graphic pointGraphic = new Graphic(point, simpleMarkerSymbol);

        //Line connecting Patiala to Ludhiana
        //Polygon to set boundaries for Punjab

        // add the point graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(pointGraphic);

        PointCollection polylinePoints = new PointCollection(SpatialReferences.getWgs84());

        polylinePoints.add(new Point(76.3779762315455, 30.35206725584051));  //Coordinates for Patiala
        polylinePoints.add(new Point(75.84797886088658, 30.897277438918845)); //Coordinates for Ludhiana


        // create a polyline geometry from the point collection
        Polyline polyline = new Polyline(polylinePoints);

        // create a blue line symbol for the polyline
        SimpleLineSymbol polylineSymbol =
                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0063FF, 3);

        // create a polyline graphic with the polyline geometry and symbol
        Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);

        // add the polyline graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(polylineGraphic);

        //Polygon to set boundary for Punjab
        PointCollection polygonPoints = new PointCollection(SpatialReferences.getWgs84());
        polygonPoints.add(new Point(75.97598444334407, 29.747050490937163));
        polygonPoints.add(new Point(76.8969565277437, 30.378165461109706));
        polygonPoints.add(new Point(76.35409615241204, 31.423306981655326));
        polygonPoints.add(new Point(75.58977033128933, 32.089277662799894));
        polygonPoints.add(new Point(74.88216127798243, 32.04350272733626));
        polygonPoints.add(new Point(74.49054558226999, 31.722437966565487));
        polygonPoints.add(new Point(74.6444910626535, 31.43713414543788));
        polygonPoints.add(new Point(74.56616791917344, 31.086218969042122));
        polygonPoints.add(new Point(73.89365781823182, 29.978057935915405));

        // create a polygon geometry from the point collection
        Polygon polygon = new Polygon(polygonPoints);

        // create an orange fill symbol with 20% transparency and the blue simple line symbol
        SimpleFillSymbol polygonFillSymbol =
                new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0x80FF5733, blueOutlineSymbol);

        // create a polygon graphic from the polygon geometry and symbol
        Graphic polygonGraphic = new Graphic(polygon, polygonFillSymbol);
        // add the polygon graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(polygonGraphic);

    }

    /**
     * Stops and releases all resources used in application.
     */
    @Override
    public void stop() {

        if (mapView != null) {
            mapView.dispose();
        }
    }
}
