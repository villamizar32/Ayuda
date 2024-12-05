import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Enumeración para representar los tipos de servicios disponibles
enum TipoServicio {
    CAMBIO_DE_OLEO,
    REPARACION_DE_MOTOR,
    CAMBIO_DE_BATERIA
}

// Interfaz para definir comportamientos comunes de los servicios
interface Servicio {
    void realizarServicio();
    double obtenerCosto();
        void mostrarDetalles();
    }
    
    // Clase abstracta que será la base de las clases derivadas
    abstract class Reparacion {
        protected String descripcion;
        protected double costo;
    
        public Reparacion(String descripcion, double costo) {
            this.descripcion = descripcion;
            this.costo = costo;
        }
    
        public abstract void mostrarDetalles();
    
        public double obtenerCosto() {
            return costo;
        }
    
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    // Clase JavaBean para encapsular los datos del vehículo
    class Vehiculo {
        private String placa;
        private String marca;
        private String modelo;
    
        public Vehiculo(String placa, String marca, String modelo) {
            this.placa = placa;
            this.marca = marca;
            this.modelo = modelo;
        }
    
        public String getPlaca() {
            return placa;
        }
    
        public String getMarca() {
            return marca;
        }
    
        public String getModelo() {
            return modelo;
        }
    }
    
    // Clase concreta que extiende de Reparacion e implementa la interfaz Servicio
    class CambioDeOleo extends Reparacion implements Servicio {
        public CambioDeOleo(String descripcion, double costo) {
            super(descripcion, costo);
        }
    
        @Override
        public void realizarServicio() {
            System.out.println("Realizando el cambio de aceite...");
        }
    
        @Override
        public void mostrarDetalles() {
            System.out.println("Servicio: Cambio de aceite\nDescripción: " + descripcion + "\nCosto: " + costo);
        }
    }
    
    // Clase concreta para Reparacion de Motor
    class ReparacionDeMotor extends Reparacion implements Servicio {
        public ReparacionDeMotor(String descripcion, double costo) {
            super(descripcion, costo);
        }
    
        @Override
        public void realizarServicio() {
            System.out.println("Realizando la reparación del motor...");
        }
    
        @Override
        public void mostrarDetalles() {
            System.out.println("Servicio: Reparación de motor\nDescripción: " + descripcion + "\nCosto: " + costo);
        }
    }
    
    // Clase que representa la interfaz gráfica con validaciones
    public class TallerDeReparacionGUI {
        private JFrame frame;
        private JTextField txtPlaca, txtMarca, txtModelo, txtCosto;
        private JTextArea txtAreaDetalles;
        private JComboBox<TipoServicio> cmbTipoServicio;
    
        public TallerDeReparacionGUI() {
            // Configuración de la ventana principal
            frame = new JFrame("Taller de Reparación");
            frame.setLayout(new FlowLayout());
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            // Campos de entrada de datos
            frame.add(new JLabel("Placa del Vehículo:"));
            txtPlaca = new JTextField(15);
            frame.add(txtPlaca);
    
            frame.add(new JLabel("Marca:"));
            txtMarca = new JTextField(15);
            frame.add(txtMarca);
    
            frame.add(new JLabel("Modelo:"));
            txtModelo = new JTextField(15);
            frame.add(txtModelo);
    
            frame.add(new JLabel("Costo del Servicio:"));
            txtCosto = new JTextField(15);
            frame.add(txtCosto);
    
            // ComboBox para seleccionar tipo de servicio
            frame.add(new JLabel("Tipo de Servicio:"));
            cmbTipoServicio = new JComboBox<>(TipoServicio.values());
            frame.add(cmbTipoServicio);
    
            // Área de texto para mostrar los detalles
            txtAreaDetalles = new JTextArea(5, 30);
            frame.add(new JScrollPane(txtAreaDetalles));
    
            // Botón para procesar la reparación
            JButton btnProcesar = new JButton("Procesar Reparación");
            btnProcesar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    procesarReparacion();
                }
            });
            frame.add(btnProcesar);
    
            // Mostrar la ventana
            frame.setVisible(true);
        }
    
        // Método para procesar la reparación
        private void procesarReparacion() {
            // Validación básica
            if (txtPlaca.getText().isEmpty() || txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
                return;
            }
    
            String placa = txtPlaca.getText();
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            double costo = 0;
    
            try {
                costo = Double.parseDouble(txtCosto.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "El costo debe ser un número válido.");
                return;
            }
    
            // Crear el vehículo
            Vehiculo vehiculo = new Vehiculo(placa, marca, modelo);
    
            // Crear el servicio seleccionado
            Servicio servicio = null;
            TipoServicio tipo = (TipoServicio) cmbTipoServicio.getSelectedItem();
    
            switch (tipo) {
                case CAMBIO_DE_OLEO:
                    servicio = new CambioDeOleo("Cambio de aceite de motor", costo);
                    break;
                case REPARACION_DE_MOTOR:
                    servicio = new ReparacionDeMotor("Reparación de motor", costo);
                    break;
                case CAMBIO_DE_BATERIA:
                    // Otro servicio podría ser implementado aquí
                    break;
            }
    
            // Mostrar los detalles del servicio
            txtAreaDetalles.setText("Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "\nPlaca: " + vehiculo.getPlaca());
            if (servicio != null) {
                servicio.mostrarDetalles();
            servicio.realizarServicio();
        }
    }

    public static void main(String[] args) {
        new TallerDeReparacionGUI();
    }
}
