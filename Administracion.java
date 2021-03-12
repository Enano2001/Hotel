
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

class noExisteReserva extends Exception {
	public noExisteReserva() {
		super(" No Existe la reserva");
	}
}

class noSeEncuentraReserva extends Exception {
	public noSeEncuentraReserva() {
		super(" No se encuentra la reserva");
	}
}

class yaExisteReserva extends Exception {
	public yaExisteReserva() {
		super(" Ya existe la reserva");
	}
}

public class Administracion {

	Reserva reservas[];

	public boolean existeReserva(String codigoReserva) throws noExisteReserva {
		int i = 0;
		while (i < reservas.length && !codigoReserva.equals(reservas[i].getCodigoReserva())) {
			i++;
		}
		if (codigoReserva.equals(reservas[i].getCodigoReserva())) {
			return true;
		} else {
			throw new noExisteReserva();
		}
	}

	public Reserva buscarReserva(String codigoReserva) throws noSeEncuentraReserva, noExisteReserva {
		int i = 0;
		if (existeReserva(codigoReserva) == true) {
			while (i < reservas.length && !codigoReserva.equals(reservas[i].getCodigoReserva())) {
				i++;
			}
			return reservas[i];
		} else {
			throw new noSeEncuentraReserva();
		}
	}

	public void addReserva(String codigo, String cedulaCliente, String plan, Date fechaInicio, Date fechaFinal,
			String codigoEmpleado) throws yaExisteReserva, noExisteReserva {

		if (existeReserva(codigo) == true) {
			throw new yaExisteReserva();
		} else {
			System.arraycopy(reservas, 0, reservas, 0, reservas.length + 1);
			Reserva r = new Reserva();
			reservas[reservas.length] = r;
			r.setCedulaCliente(cedulaCliente);
			r.setCodigoEmpleado(codigoEmpleado);
			r.setFechaInicio(fechaInicio);
			r.setFechaFinal(fechaFinal);
			r.setPlan(plan);

		}

	}

	public void eliminarReserva(String codigoReserva) throws noExisteReserva {
		Reserva aux;
		int i = 0;
		while (i < reservas.length && !codigoReserva.equalsIgnoreCase(reservas[i].getCodigoReserva())) {
			i++;
		}
		if (codigoReserva.equalsIgnoreCase(reservas[i].getCodigoReserva())) {
			aux = reservas[i];
			reservas[i] = reservas[reservas.length];
			reservas[reservas.length] = aux;
			System.arraycopy(reservas, 0, reservas, 0, reservas.length - 1);
		} else {
			throw new noExisteReserva();
		}
	}
	
	public void imprimirReserva(String direccion, String codigo) throws IOException, noExisteReserva, noSeEncuentraReserva {
		
		FileWriter fw = new FileWriter(direccion);
		PrintWriter pw = new PrintWriter(fw);
		
		if(existeReserva(codigo)==true) {
			pw.print(buscarReserva(codigo));
		}
		pw.close();
	}
}
  
