package test;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;



/**This is the test harness class to test the infopopup dialog.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class TestInfoPopup
{
	public static void main(String[] args) 
	{
		final Display display = new Display ();
		final Shell shell = new Shell (display, SWT.DIALOG_TRIM); 
		
		Button btn = new Button( shell , SWT.PUSH );
		btn.setText("Press to see the InfoPopup");
		btn.setBounds(90, 10, 200, 30);
		
		btn.addSelectionListener( new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent se) 
			{
				//You can set the sixe of the Rectangle
				Rectangle rect = new Rectangle(50,220,200,110);
				//InfoPopup pop = new InfoPopup( new Shell() , rect ,"Information for you","Select and press ESC to close");
				//pop.setText("This is a special case of info popup dialog box which is similar to Eclipse InfoPopup.");
				//pop.open();
			}
		}
		);
		shell.layout(true, true);
		final Point newSize = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		newSize.x += 90; // adicionar a posi��o a esquerda
		shell.setSize(newSize);
		shell.open ();
		while (!shell.isDisposed ()) 
		{
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

}
