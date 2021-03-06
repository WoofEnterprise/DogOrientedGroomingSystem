package org.woofenterprise.dogs.service;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprise.dogs.dao.AppointmentDAO;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.service.utils.BaseTestCase;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class AppointmentServiceImplTest extends BaseTestCase {

    @Mock
    private AppointmentDAO appointmentDAO;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    public void testCreate() {
        Appointment a = mock(Appointment.class);
        appointmentService.createAppointment(a);
        verify(appointmentDAO).create(a);
    }

    @Test
    public void testFindById() {
        Long id = 5L;
        Appointment returnedAppointment = appointmentService.findAppointmentById(id);
        verify(appointmentDAO).findById(id);
    }

    @Test
    public void testCancel() {
        Appointment appointment = mock(Appointment.class);
        appointmentService.cancelAppointment(appointment);
        verify(appointmentDAO).delete(appointment);
    }

    @Test
    public void testGetAll() {
        Collection<Appointment> result = appointmentService.getAllAppointments();
        verify(appointmentDAO).findAll();
    }
    
    @Test
    public void testGetAppointmentsForRange() {
        Date time = mock(Date.class);
        Collection<Appointment> result = appointmentService.getAllAppointmentsForRange(time, time);
        verify(appointmentDAO).findAllAppointmentsForRange(time, time);
    } 
    @Test
    public void testGetAppointmentsAfter() {
        Date time = mock(Date.class);
        Collection<Appointment> result = appointmentService.getAllAppointmentsAfter(time);
        verify(appointmentDAO).findAllAppointmentsAfter(time);
    }
    @Test
    public void testGetAppointmentsBefore() {
        Date time = mock(Date.class);
        Collection<Appointment> result = appointmentService.getAllAppointmentsBefore(time);
        verify(appointmentDAO).findAllAppointmentsBefore(time);
    }
}
