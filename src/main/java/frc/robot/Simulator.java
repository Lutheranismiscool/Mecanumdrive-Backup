// // This is not a working simulator, I broke the code and made something better. :) -KJZ

// package frc.robot;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.io.File;
// import java.util.Arrays;
// import java.util.Random;

// public class BlockSimulator extends JPanel {
//     private static final int BLOCK_SIZE = 50; // Size of each block
//     private static final int NUM_BLOCKS = 100; // Total number of blocks
//     private final Block[] blocks;
//     private double cameraAngleY = 0; // Camera rotation around Y-axis
//     private double cameraAngleX = 0; // Camera rotation around X-axis
//     private double cameraDistance = 700; // Distance from camera to blocks
//     private BufferedImage texture; // Texture for blocks
//     private Point mousePoint = new Point(0, 0);

//     public BlockSimulator() {
//         blocks = new Block[NUM_BLOCKS];
//         Random rand = new Random();

//         // Initialize blocks with random positions
//         for (int i = 0; i < NUM_BLOCKS; i++) {
//             double x = rand.nextDouble() * 2000 - 1000;
//             double y = rand.nextDouble() * 400; // Initial height
//             double z = rand.nextDouble() * 2000 - 1000;
//             blocks[i] = new Block(x, y, z);
//         }

//         loadImage();

//         setPreferredSize(new Dimension(800, 600));
//         setBackground(Color.WHITE);
//         addMouseMotionListener(new MouseMotionAdapter() {
//             @Override
//             public void mouseMoved(MouseEvent e) {
//                 mousePoint.setLocation(e.getX(), e.getY());
//             }
//         });
//         addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {
//                 switch (e.getKeyCode()) {
//                     case KeyEvent.VK_A: // Rotate camera left
//                         cameraAngleY -= 0.1;
//                         break;
//                     case KeyEvent.VK_D: // Rotate camera right
//                         cameraAngleY += 0.1;
//                         break;
//                     case KeyEvent.VK_W: // Move camera up
//                         cameraDistance -= 10; // Zoom in
//                         break;
//                     case KeyEvent.VK_S: // Move camera down
//                         cameraDistance += 10; // Zoom out
//                         break;
//                     case KeyEvent.VK_UP: // Rotate camera up
//                         cameraAngleX += 0.1;
//                         break;
//                     case KeyEvent.VK_DOWN: // Rotate camera down
//                         cameraAngleX -= 0.1;
//                         break;
//                 }
//                 repaint();
//             }
//         });

//         setFocusable(true);
//         Timer timer = new Timer(16, e -> update());
//         timer.start();
//     }

//     private void loadImage() {
//         JFileChooser fileChooser = new JFileChooser();
//         fileChooser.setDialogTitle("Select a small image for the block faces");
//         if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//             File file = fileChooser.getSelectedFile();
//             try {
//                 texture = ImageIO.read(file);
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         }
//     }

//     private void update() {
//         for (Block block : blocks) {
//             block.update(mousePoint);
//         }
//         repaint();
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         Graphics2D g2d = (Graphics2D) g;

//         // Sort blocks based on distance from camera for correct rendering order
//         Arrays.sort(blocks, (b1, b2) -> Double.compare(b1.getDistanceToCamera(cameraAngleY, cameraAngleX), b2.getDistanceToCamera(cameraAngleY, cameraAngleX)));

//         // Draw blocks
//         for (Block block : blocks) {
//             block.draw(g2d, cameraAngleY, cameraAngleX, cameraDistance);
//         }
//     }

//     private class Block {
//         private double x, y, z; // Position

//         public Block(double x, double y, double z) {
//             this.x = x;
//             this.y = y;
//             this.z = z;
//         }

//         public void update(Point mouse) {
//             // Update block logic if needed
//         }

//         public double getDistanceToCamera(double camY, double camX) {
//             return Math.sqrt(x * x + y * y + z * z);
//         }

//         public void draw(Graphics2D g2d, double camY, double camX, double camDistance) {
//             // Project the 3D position to 2D for rendering
//             int[][] projected = new int[8][2];
//             for (int i = 0; i < 8; i++) {
//                 projected[i] = project(i, camY, camX, camDistance);
//             }

//             // Draw all six sides of the cube with color and image
//             drawFace(g2d, projected, 0, 1, 2, 3); // Front
//             drawFace(g2d, projected, 4, 5, 6, 7); // Back
//             drawFace(g2d, projected, 0, 1, 5, 4); // Left
//             drawFace(g2d, projected, 2, 3, 7, 6); // Right
//             drawFace(g2d, projected, 1, 3, 7, 5); // Top
//             drawFace(g2d, projected, 0, 4, 6, 2); // Bottom
//         }

//         private void drawFace(Graphics2D g2d, int[][] projected, int v1, int v2, int v3, int v4) {
//             // Create a polygon for the face
//             Polygon face = new Polygon();
//             face.addPoint(projected[v1][0], projected[v1][1]);
//             face.addPoint(projected[v2][0], projected[v2][1]);
//             face.addPoint(projected[v3][0], projected[v3][1]);
//             face.addPoint(projected[v4][0], projected[v4][1]);

//             // Draw blue face with black border
//             g2d.setColor(Color.BLUE);
//             g2d.fillPolygon(face);
//             g2d.setColor(Color.BLACK);
//             g2d.drawPolygon(face); // Draw outline

//             // Draw small image centered on the face
//             int imgWidth = 50; // Set the desired width of the image
//             int imgHeight = 50; // Set the desired height of the image
//             int centerX = (projected[v1][0] + projected[v2][0] + projected[v3][0] + projected[v4][0]) / 4 - imgWidth / 2;
//             int centerY = (projected[v1][1] + projected[v2][1] + projected[v3][1] + projected[v4][1]) / 4 - imgHeight / 2;

//             if (texture != null) {
//                 g2d.drawImage(texture, centerX, centerY, imgWidth, imgHeight, null);
//             }
//         }

//         private int[] project(int vertexIndex, double camY, double camX, double camDistance) {
//             double[][] vertices = {
//                 {x - BLOCK_SIZE / 2, y - BLOCK_SIZE / 2, z - BLOCK_SIZE / 2}, // Front-bottom-left
//                 {x + BLOCK_SIZE / 2, y - BLOCK_SIZE / 2, z - BLOCK_SIZE / 2}, // Front-bottom-right
//                 {x + BLOCK_SIZE / 2, y + BLOCK_SIZE / 2, z - BLOCK_SIZE / 2}, // Front-top-right
//                 {x - BLOCK_SIZE / 2, y + BLOCK_SIZE / 2, z - BLOCK_SIZE / 2}, // Front-top-left
//                 {x - BLOCK_SIZE / 2, y - BLOCK_SIZE / 2, z + BLOCK_SIZE / 2}, // Back-bottom-left
//                 {x + BLOCK_SIZE / 2, y - BLOCK_SIZE / 2, z + BLOCK_SIZE / 2}, // Back-bottom-right
//                 {x + BLOCK_SIZE / 2, y + BLOCK_SIZE / 2, z + BLOCK_SIZE / 2}, // Back-top-right
//                 {x - BLOCK_SIZE / 2, y + BLOCK_SIZE / 2, z + BLOCK_SIZE / 2}  // Back-top-left
//             };

//             // Rotate around Y-axis
//             double angleYCos = Math.cos(camY);
//             double angleYSin = Math.sin(camY);
//             double angleXCos = Math.cos(camX);
//             double angleXSin = Math.sin(camX);

//             double tempX = vertices[vertexIndex][0] * angleYCos + vertices[vertexIndex][2] * angleYSin;
//             double tempZ = -vertices[vertexIndex][0] * angleYSin + vertices[vertexIndex][2] * angleYCos;

//             // Rotate around X-axis
//             double tempY = vertices[vertexIndex][1] * angleXCos - tempZ * angleXSin;
//             tempZ = vertices[vertexIndex][1] * angleXSin + tempZ * angleXCos;

//             // Perspective projection
//             double perspective = camDistance / (camDistance + tempZ);
//             int projectedX = (int) (tempX * perspective) + getWidth() / 2;
//             int projectedY = (int) (tempY * perspective) + getHeight() / 2;

//             return new int[]{projectedX, projectedY};
//         }
//     }

//     public static void main(String[] args) {
//         JFrame frame = new JFrame("3D Block Simulator");
//         BlockSimulator simulator = new BlockSimulator();
//         frame.add(simulator);
//         frame.pack();
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setVisible(true);
//     }
// }
