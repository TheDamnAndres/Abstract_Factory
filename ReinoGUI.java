import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReinoGUI extends JFrame {
    private JButton elfButton;
    private JButton orcButton;
    private JPanel principal;
    private JLabel lbCastillo;
    private JLabel lbRey;
    private JLabel lbEjercito;

    public ReinoGUI() {

        elfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                crearReino(FactoryMaker.ReinoType.ELF);
            }
        });
        orcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearReino(FactoryMaker.ReinoType.ORC);
            }
        });
    }

    private void crearReino(FactoryMaker.ReinoType type) {
        ReinoFactory factory = FactoryMaker.makeFactory(type);
        lbCastillo.setText("CASTILLO " + factory.setCastillo().getCastillo());
        lbRey.setText("REY: " + factory.setRey().getRey());
        lbEjercito.setText("EJERCITO: " + factory.setEjercito().getEjercito());
    }

    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame("ReinoGUI");
        frame.setContentPane(new ReinoGUI().principal);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Interfaces definen los métodos que deben implementar los objetos
    public interface Castillo {
        String getCastillo();
    }

    public interface Rey {
        String getRey();
    }

    public interface Ejercito {
        String getEjercito();
    }

    // implementan las interfaces mencionadas, proporcionando detalles específicos de cada tipo de reino
    
    //ELFOS
    public class ElfoCastillo implements Castillo {
        static final String DESCRIPTION = "DE ELFOS!";
        @Override
        public String getCastillo() { return DESCRIPTION; }
    }

    public class ElfoRey implements Rey {
        static final String DESCRIPTION = "ELENION!";
        @Override
        public String getRey() { return DESCRIPTION; }
    }

    public class ElfoEjercito implements Ejercito {
        static final String DESCRIPTION = "CENTINELAS DEL BOSQUE ETERNO!";
        @Override
        public String getEjercito() { return DESCRIPTION; }
    }

   //OGROS
    public class OrcoCastillo implements Castillo {
        static final String DESCRIPTION = "DE OGROS!";
        @Override
        public String getCastillo() { return DESCRIPTION; }
    }

    public class OrcoRey implements Rey {
        static final String DESCRIPTION = "GROMMASH!";
        @Override
        public String getRey() { return DESCRIPTION; }
    }

    public class OrcoEjercito implements Ejercito {
        static final String DESCRIPTION = "WARBRINGERS OF THE WASTELAND!";
        @Override
        public String getEjercito() { return DESCRIPTION; }
    }

    /* Esta es la interfaz de la fábrica abstracta. Declara métodos para crear los objetos
    Castle, King y Army. Estos métodos son implementados por las fábricas concretas.*/

    public interface ReinoFactory {
        Castillo setCastillo();
        Rey setRey();
        Ejercito setEjercito();
    }

    
    public class ElfosReinoFactory implements ReinoFactory {
        public Castillo setCastillo() {
            return new ElfoCastillo();
        }
        public Rey setRey() {
            return new ElfoRey();
        }
        public Ejercito setEjercito() {
            return new ElfoEjercito();
        }
    }

    public class OrcosReinoFactory implements ReinoFactory {
        public Castillo setCastillo() {
            return new OrcoCastillo();
        }
        public Rey setRey() {
            return new OrcoRey();
        }
        public Ejercito setEjercito() {
            return new OrcoEjercito();
        }
    }

    /* Este es un enfoque típico en el patrón Abstract Factory, donde un "Factory Maker"
      o "Factory Provider" centraliza la lógica de decisión para la creación de diferentes fábricas.*/
    public static class FactoryMaker {

        //se utiliza un enum ReinoType para determinar el tipo de fábrica que debe crear.
        public enum ReinoType { ELF, ORC }

        public static ReinoFactory makeFactory(ReinoType type) {

            switch (type) {
                case ELF:
                    return new ReinoGUI().new ElfosReinoFactory();
                case ORC:
                    return new ReinoGUI().new OrcosReinoFactory();
                default:
                    throw new IllegalArgumentException("ReinoType not supported.");
            }
        }
    }
}