package jniosemu.gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.*;

import jniosemu.events.*;
import jniosemu.emulator.*;

/**
 * Creates and manages the GUI component of the dipswitches.
 */
 public class GUIIODipswitches extends JPanel
											 implements ActionListener, EventObserver {

	/**
	 * Reference to EventManager used to receive
	 * and send events.
	 */
	private EventManager eventManager;

	private static int DIPSWITCH_COUNT = 4;

	private JLabel[] dipswitches = new JLabel[DIPSWITCH_COUNT];

	private ImageIcon[] icons = new ImageIcon[2];

	/**
	 * Initiates the creation of GUI components and adds itself to
	 * the Event Manager as an observer.
	 *
	 * @post      eventManager reference is set for this object.
	 * @calledby  GUIManager.setup()
	 * @calls     setup(), EventManager.addEventObserver()
	 *
	 * @param  eventManager  The Event Manager object.
	 */
	public GUIIODipswitches(EventManager eventManager)
	{
		super();

		this.eventManager = eventManager;

		setup();

		// add events to listen to
		String[] events = {
			Events.EVENTID_COMPILATION_DONE,
			Events.EVENTID_PC_CHANGE
		};
		this.eventManager.addEventObserver(events, this);
	}

	/**
	 * Setup GUI components and attributes.
	 *
	 * @post      components created and added to panel
	 * @calledby  GUIIODipswitches
	 */
	private void setup()
	{
		this.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(3, 3, 0, 0),
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)
			)
		);

		// load image icons
		icons[0] = new ImageIcon("graphics/io/dipswitch_unset.png");
		icons[1] = new ImageIcon("graphics/io/dipswitch_set.png");

		// create dipswitches		
		for(int i = 0; i < dipswitches.length; i++)
		{
			dipswitches[i] = new JLabel(icons[0]);
			this.add(dipswitches[i]);
		}
	}

	/**
	 *
	 */
	public void setButton(int dipswitchIndex, int value)
	{
		if (dipswitchIndex < 0 || dipswitchIndex >= DIPSWITCH_COUNT)
		{
			System.out.println("GUIIODipswitches.setButton(): Invalid dipswitchIndex " + dipswitchIndex);
			return;
		}
			
		if (value == 0)
			dipswitches[dipswitchIndex].setIcon(icons[0]);
		else
			dipswitches[dipswitchIndex].setIcon(icons[1]);
	}

	/**
	 *
	 */
	public void resetDipswitches()
	{
		for(int i = 0; i < dipswitches.length; i++)
		{
			setButton(i, 0);
		}
	}

	public void update(String eventIdentifier, Object obj)
	{
		if (eventIdentifier.equals(Events.EVENTID_UPDATE_DIPSWITCHES))
		{
			// setButton()
		}
		else if (eventIdentifier.equals(Events.EVENTID_RESET))
		{
			resetDipswitches();
		}
	}

	/**
	 * Invoked when a GUI action occurs, forwards it as
	 * an event to the EventManager object.
	 *
	 * @calls     EventManager.sendEvent()
	 *
	 * @param  e  action event object
	 */
	public void actionPerformed(ActionEvent e) {
			eventManager.sendEvent(e.getActionCommand());
	}

}