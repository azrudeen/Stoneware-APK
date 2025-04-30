package com.example.Stoneware;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SquareFeetCalculator extends AppCompatActivity {

    private EditText lengthInput, widthInput, tileSizeInput, costInput;
    private Spinner shapeSpinner, lengthUnitSpinner, widthUnitSpinner;
    private Button calculateButton, downloadButton;
    private TextView resultArea, resultTiles, resultCost, resultTileSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_calculator);

        // UI Components
        lengthInput = findViewById(R.id.lengthInput);
        widthInput = findViewById(R.id.widthInput);
        tileSizeInput = findViewById(R.id.tileSizeInput);
        costInput = findViewById(R.id.costInput);
        shapeSpinner = findViewById(R.id.shapeSpinner);
        lengthUnitSpinner = findViewById(R.id.lengthUnitSpinner);
        widthUnitSpinner = findViewById(R.id.widthUnitSpinner);
        calculateButton = findViewById(R.id.calculateButton);
        downloadButton = findViewById(R.id.downloadButton);

        resultArea = findViewById(R.id.resultArea);
        resultTiles = findViewById(R.id.resultTiles);
        resultCost = findViewById(R.id.resultCost);
        resultTileSize = findViewById(R.id.resultTileSize);

        // Setup spinners
        String[] units = {"feet", "meters", "inches"};
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        lengthUnitSpinner.setAdapter(unitAdapter);
        widthUnitSpinner.setAdapter(unitAdapter);

        String[] shapes = {"Rectangle", "Circle", "L-Shape"};
        ArrayAdapter<String> shapeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, shapes);
        shapeSpinner.setAdapter(shapeAdapter);

        calculateButton.setOnClickListener(v -> calculateArea());
        downloadButton.setOnClickListener(v -> generatePDF());
    }

    private void calculateArea() {
        try {
            double length = convertToFeet(Double.parseDouble(lengthInput.getText().toString()), lengthUnitSpinner.getSelectedItem().toString());
            double width = convertToFeet(Double.parseDouble(widthInput.getText().toString()), widthUnitSpinner.getSelectedItem().toString());
            String shape = shapeSpinner.getSelectedItem().toString();

            double areaSqFt = 0;
            switch (shape) {
                case "Rectangle":
                    areaSqFt = length * width;
                    break;
                case "Circle":
                    double radius = length / 2;
                    areaSqFt = Math.PI * radius * radius;
                    break;
                case "L-Shape":
                    areaSqFt = (length * width) * 0.75;
                    break;
            }

            String[] tileParts = tileSizeInput.getText().toString().toLowerCase().split("x");
            if (tileParts.length != 2) {
                clearResults();
                resultArea.setText("Invalid tile size format.");
                return;
            }

            double tileLength = Double.parseDouble(tileParts[0].trim());
            double tileWidth = Double.parseDouble(tileParts[1].trim());
            double tileArea = tileLength * tileWidth;
            int tilesNeeded = (int) Math.ceil(areaSqFt / tileArea);

            String costStr = costInput.getText().toString().trim();
            double totalCost = 0;
            if (!costStr.isEmpty()) {
                double costPerSqFt = Double.parseDouble(costStr);
                totalCost = areaSqFt * costPerSqFt;
            }

            resultArea.setText(String.format("%.2f sq ft", areaSqFt));
            resultTiles.setText(String.valueOf(tilesNeeded));
            resultTileSize.setText(String.format("%.2f x %.2f ft", tileLength, tileWidth));
            resultCost.setText(totalCost > 0 ? "₹" + String.format("%.2f", totalCost) : "-");

        } catch (Exception e) {
            clearResults();
            resultArea.setText("⚠️ Invalid input");
        }
    }

    private double convertToFeet(double value, String unit) {
        switch (unit.toLowerCase()) {
            case "meters":
                return value * 3.28084;
            case "inches":
                return value / 12;
            default:
                return value;
        }
    }

    private void clearResults() {
        resultArea.setText("-");
        resultTiles.setText("-");
        resultTileSize.setText("-");
        resultCost.setText("-");
    }

    private void generatePDF() {
        PdfDocument document = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        int y = 25;
        paint.setTextSize(12);
        canvas.drawText("🧾 Tile Calculation Result", 10, y, paint); y += 25;
        canvas.drawText("Shape: " + shapeSpinner.getSelectedItem().toString(), 10, y, paint); y += 20;
        canvas.drawText("Area: " + resultArea.getText().toString(), 10, y, paint); y += 20;
        canvas.drawText("Tiles Needed: " + resultTiles.getText().toString(), 10, y, paint); y += 20;
        canvas.drawText("Tile Size: " + resultTileSize.getText().toString(), 10, y, paint); y += 20;
        canvas.drawText("Estimated Cost: " + resultCost.getText().toString(), 10, y, paint);

        document.finishPage(page);

        String fileName = "TileCalculation_" + System.currentTimeMillis() + ".pdf";

        try {
            OutputStream outputStream;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                // For Android 10+
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

                ContentResolver resolver = getContentResolver();
                Uri uri = resolver.insert(MediaStore.Files.getContentUri("external"), values);

                if (uri == null) throw new IOException("Failed to create MediaStore entry");

                outputStream = resolver.openOutputStream(uri);
            } else {
                // For Android 7–9
                File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (!downloadsDir.exists()) downloadsDir.mkdirs();

                File file = new File(downloadsDir, fileName);
                outputStream = new FileOutputStream(file);
            }

            if (outputStream != null) {
                document.writeTo(outputStream);
                outputStream.flush();
                outputStream.close();
                Toast.makeText(this, "✅ PDF saved to Downloads", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "❌ Failed to save PDF", Toast.LENGTH_SHORT).show();
            }

            document.close();

        } catch (IOException e) {
            Toast.makeText(this, "❌ Error creating PDF", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}